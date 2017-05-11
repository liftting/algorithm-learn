# coding=utf-8
import sys

# array *12*3*4*0  将*放前面
#倒着 复制，将前面数复制到后面
def change(array):
    print(array)
    i = len(array) -1
    j = len(array) - 1
    while i>=0:
        if array[i]>=0 and array[i]<=9 :
            array[j] = array[i]
            j-=1
        i-=1
        
    while j>=0:
        array[j] = '*'
        j-=1
    
    print(array)

            

if __name__ == '__main__':
    change(['*',1,2,'*',3,'*','*',4,'*',5,6])