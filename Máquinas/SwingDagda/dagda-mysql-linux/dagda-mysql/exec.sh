#!/bin/bash

if [ ! -d /home/ubuntu/Desktop/ArqUsoDagda ]

        then
        cd /home/ubuntu/Desktop
        mkdir ArqUsoDagda
        cd ArqUsoDagda
        ps -e -o pid,user,time,pmem | sort -n -k 3 -r | grep ubuntu > arqUso.txt
else
        cd /home/ubuntu/Desktop/ArqUsoDagda
        rm arqUso.txt
        cd ..
        rmdir ArqUsoDagda
        mkdir ArqUsoDagda
        cd ArqUsoDagda
        ps -e -o pid,user,time,pmem | sort -n -k 3 -r | grep ubuntu > arqUso.txt
fi

