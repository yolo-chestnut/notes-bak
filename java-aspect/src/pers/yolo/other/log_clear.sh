#!/usr/bin/env bash

log_path=""
log_file="${log_path}/log_clear_$(date +%Y-%m-%d).log"
log_dirs=("path1" "path2")
clear_date=+1

if [ ! -d "${log_path}" ]; then
  mkdir "${log_path}"
fi

for log_dir in "${log_dirs[@]}"; do
  echo "清理日志文件，当前时间为：[$(date +%F\ %T)]" >>"${log_file}"
  find "${log_dir}" -type f \( -name \*.log -o -name \*.txt \) -mmin ${clear_date} -print0 | xargs -0 -I file sh -c "echo 删除 file >> ${log_file}; rm file;"
  echo " " >>"${log_file}"
done
