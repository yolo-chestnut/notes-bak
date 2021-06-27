#!/usr/bin/env bash

names=("name1" "name2")
source_file=""
target_path_prefix="/tmp"

for name in "${names[@]}"; do
  cp "${source_file}" "${target_path_prefix}/${name}.docx"
done
