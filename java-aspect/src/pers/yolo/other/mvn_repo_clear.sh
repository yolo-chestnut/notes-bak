#!/usr/bin/env bash

repo_path="$1"

echo 搜索开始...
find "${repo_path}" -type f \( -name \*.repositories -o -name \*.lastUpdated \) -print0 | xargs -0 -I file sh -c "echo 删除 $(basename file); rm file"
echo 搜索结束...
