#!/bin/bash

curl -X POST -u user:user -H 'Content-Type: application/json' -d '{"name": "Test", "age": 45, "birthday": "1971-11-13"}' "$1/entities/"
