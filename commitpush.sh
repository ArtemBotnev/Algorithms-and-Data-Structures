#!/bin/bash

#adds, commits and pushes to remote full root package
#example: $./commitpush.sh my new super commit

#check your remote
remote=origin
message="$*"

branch_name=$(git rev-parse --abbrev-ref HEAD)
git add -A
git commit -m "$message"
if [[ $? -eq 0 ]]
then
    git push ${remote} ${branch_name}
fi