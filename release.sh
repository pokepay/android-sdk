#!/bin/bash

# Release script for Pokepay Android SDK
# This script automates the release process including build, tag, publish, and deployment

set -e  # Exit immediately if a command exits with a non-zero status

echo "=========================================="
echo "Pokepay Android SDK Release Script"
echo "=========================================="
echo ""

# Extract version from pokepaylib/build.gradle
echo "Reading version from pokepaylib/build.gradle..."
VERSION=$(grep -E "versionName\s+\"" pokepaylib/build.gradle | sed -E 's/.*versionName\s+"([^"]+)".*/\1/')

if [ -z "$VERSION" ]; then
    echo "Error: Could not extract version from pokepaylib/build.gradle"
    exit 1
fi

echo "Found version: $VERSION"
echo ""

# Extract credentials from gradle.properties
echo "Reading credentials from gradle.properties..."
OSSRH_USERNAME=$(grep "^ossrhUsername=" gradle.properties | cut -d'=' -f2)
OSSRH_PASSWORD=$(grep "^ossrhPassword=" gradle.properties | cut -d'=' -f2)

if [ -z "$OSSRH_USERNAME" ] || [ -z "$OSSRH_PASSWORD" ]; then
    echo "Error: Could not extract credentials from gradle.properties"
    echo "Please ensure ossrhUsername and ossrhPassword are set in gradle.properties"
    exit 1
fi

echo "Credentials loaded successfully"
echo ""

# Confirm with user
read -p "Proceed with release version $VERSION? (y/n): " -n 1 -r
echo ""
if [[ ! $REPLY =~ ^[Yy]$ ]]; then
    echo "Release cancelled by user"
    exit 1
fi
echo ""

# Step 1: Clean and Build
echo "Step 1/5: Cleaning and building project..."
./gradlew clean
if [ $? -ne 0 ]; then
    echo "Error: gradlew clean failed"
    exit 1
fi

./gradlew build
if [ $? -ne 0 ]; then
    echo "Error: gradlew build failed"
    exit 1
fi
echo "Build completed successfully"
echo ""

# Step 2: Git Tag
echo "Step 2/5: Creating and pushing git tag $VERSION..."
git tag "$VERSION"
if [ $? -ne 0 ]; then
    echo "Error: git tag failed (tag may already exist)"
    exit 1
fi

git push --tags
if [ $? -ne 0 ]; then
    echo "Error: git push --tags failed"
    exit 1
fi
echo "Git tag created and pushed successfully"
echo ""

# Step 3: Publish
echo "Step 3/5: Publishing to Maven repository..."
./gradlew publish
if [ $? -ne 0 ]; then
    echo "Error: gradlew publish failed"
    exit 1
fi
echo "Published successfully"
echo ""

# Step 4: Search for open repositories
echo "Step 4/5: Searching for open repositories..."
SEARCH_RESPONSE=$(curl -s -u "${OSSRH_USERNAME}:${OSSRH_PASSWORD}" \
    -H "Accept: application/json" \
    "https://ossrh-staging-api.central.sonatype.com/manual/search/repositories?state=open")

if [ $? -ne 0 ]; then
    echo "Error: Failed to search for open repositories"
    exit 1
fi

echo "Open repositories response:"
echo "$SEARCH_RESPONSE" | jq '.' 2>/dev/null || echo "$SEARCH_RESPONSE"
echo ""

# Step 5: Upload to repository
echo "Step 5/5: Uploading to Sonatype repository..."
UPLOAD_RESPONSE=$(curl -s -u "${OSSRH_USERNAME}:${OSSRH_PASSWORD}" \
    -X POST "https://ossrh-staging-api.central.sonatype.com/manual/upload/repository/${OSSRH_USERNAME}/219.104.131.128/jp.pocket-change.pokepay.android-sdk--default-repository" \
    -H "Content-Type: application/json" \
    -d '{"publishing_type":"user_managed"}')

if [ $? -ne 0 ]; then
    echo "Error: Failed to upload to repository"
    exit 1
fi

echo "Upload response:"
echo "$UPLOAD_RESPONSE" | jq '.' 2>/dev/null || echo "$UPLOAD_RESPONSE"
echo ""

echo "=========================================="
echo "Release $VERSION completed successfully!"
echo "=========================================="
echo ""
