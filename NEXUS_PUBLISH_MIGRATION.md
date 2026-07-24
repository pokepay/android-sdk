# Migration Plan: Gradle Nexus Publish Plugin

## Background

The current release flow uses the Sonatype OSSRH compatibility API manually:

1. `./gradlew publish` — uploads artifacts via HTTP PUT to a staging area keyed by `username/client-IP/namespace`
2. `release.sh` step 4 — searches for the open staging repo via `/manual/search/repositories?state=open`
3. `release.sh` step 5 — triggers release via `/manual/upload/repository/{REPO_KEY}`

### Problems with the current approach

| Problem | Root cause | Current workaround |
|---|---|---|
| Search returns empty repos | Staging repo key includes client IP; Gradle daemon and `curl` can use different IPs | `-Djava.net.preferIPv4Stack=true` in Gradle JVM args + `-4` on curl |
| Stale empty results from search | Cloudflare CDN caches the search endpoint for 4 hours | `?_=$(date +%s)` timestamp appended to search URL |

The workarounds are fragile — a network change (new IPv4, VPN, etc.) could break the IP match again.

## Option 2: gradle-nexus/publish-plugin

The [gradle-nexus/publish-plugin](https://github.com/gradle-nexus/publish-plugin) is the standard approach for publishing to Maven Central. It:

- Creates a staging repository **explicitly via the Nexus REST API** (no IP-keyed default repo)
- Uploads artifacts to that specific staging repo
- Closes and releases the staging repo — all in one Gradle task

No IP dependency. No Cloudflare cache. Steps 4 and 5 of `release.sh` become unnecessary.

---

## Migration Steps

### 1. Add the plugin to root `build.gradle`

```groovy
plugins {
    id 'io.github.gradle-nexus.publish-plugin' version '2.0.0'
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/content/repositories/snapshots/"))
            username = ossrhUsername
            password = ossrhPassword
        }
    }
}
```

### 2. Update `pokepaylib/build.gradle`

Remove the `repositories` block from `publishing` (the nexus plugin provides the staging repo):

```groovy
// DELETE this entire block:
repositories {
    maven {
        name = "OSSRH"
        url = uri("https://ossrh-staging-api.central.sonatype.com/service/local/staging/deploy/maven2/")
        credentials {
            username = ossrhUsername
            password = ossrhPassword
        }
    }
}
```

### 3. Update `release.sh`

Replace step 3 and remove steps 4 and 5:

```bash
# Step 3: Publish + close + release (replaces old steps 3, 4, 5)
echo "Step 3/3: Publishing to Maven Central..."
./gradlew publishToSonatype closeAndReleaseSonatypeStagingRepository
if [ $? -ne 0 ]; then
    echo "Error: publish and release failed"
    exit 1
fi
echo "Published and released successfully"
```

### 4. Clean up `gradle.properties`

The IPv4 workarounds can be removed once the migration is complete:

- Remove `-Djava.net.preferIPv4Stack=true` from `org.gradle.jvmargs`

---

## Testing

Before doing a real release, test with a SNAPSHOT version:

1. Set `version = '2.0.29-SNAPSHOT'` in `pokepaylib/build.gradle`
2. Run `./gradlew publishToSonatype` — verify it uploads to the snapshot repo without errors
3. Revert the version, then do a dry-run with `./gradlew publishToSonatype` only (skip `closeAndRelease`)
4. Check the [Sonatype Central Portal](https://central.sonatype.com) to confirm the staging repo is visible and contains the right artifacts
5. Manually trigger `./gradlew closeAndReleaseSonatypeStagingRepository` and verify 2.0.x appears on Maven Central

## References

- Plugin repo: https://github.com/gradle-nexus/publish-plugin
- Sonatype Central Portal: https://central.sonatype.com
- Plugin docs for OSSRH migration: https://github.com/gradle-nexus/publish-plugin#publishing-to-maven-central-via-sonatype-ossrh
