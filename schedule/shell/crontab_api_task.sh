#!/bin/bash
source /etc/profile
SELF=$(cd $(dirname $0); pwd -P)/$(basename $0)
shpath=`dirname $SELF`

err_log="${shpath}/api_task_err.log"
#out_log="/dev/null"
out_log="${shpath}/api_task.log"
analysis_pid_file="${shpath}/api_task.pid"
analysis_mvn_home="${shpath}/../"


cd "${analysis_mvn_home}"
echo "**************启动api_task器"
mvn -e exec:java -Dexec.mainClass="com.chi.api.taobao.task.ApiTask" >> ${out_log} 2>${err_log} &
echo $!>${analysis_pid_file}
analysis_pid=`cat ${analysis_pid_file}`
echo "成功启动api_task器,PID=${analysis_pid}"
