cd %userprofile%/Desktop
md ArqUsoDagda
cd ArqUsoDagda
chcp 65001
tasklist /fi "memusage gt 100000" /fi "SESSIONNAME eq console" | sort /r /+65 > arqUso.txt