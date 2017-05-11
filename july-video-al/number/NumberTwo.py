import sys

def print_op(x):
    print(str(x)+"="+str(x&(x-1)))

if __name__ == '__main__':
    for i in range(1,21):
        print_op(i)