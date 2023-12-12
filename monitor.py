import time 
import psutil

def info():
    cpu_percent = psutil.cpu_percent()
    mem_info = psutil.virtual_memory()
    return f"The cpu percentage: {cpu_percent}% and Memory percentage: {mem_info.percent}%"

print("Hello World!")
while True:
    print(info())
    time.sleep(10)
