#!/usr/local/bin/bash

TOKEN=$(cat ~/Library/Application\ Support/minecraft/launcher_accounts.json | jq -r '.accounts.de854f920aad4796b87f9b7c5a08d9da.accessToken')

echo $TOKEN