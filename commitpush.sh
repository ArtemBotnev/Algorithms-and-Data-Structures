#!/bin/bash

#check your remote
remote=origin
message="$*"

branch_name=$(git rev-parse --abbrev-ref HEAD)
git add -A

if [[ $# -eq 0 ]]
then
    echo commit must has message
else
    git commit -m "$message"

    if [[ $? -eq 0 ]]
    then
        git push ${remote} ${branch_name}
    fi
fi