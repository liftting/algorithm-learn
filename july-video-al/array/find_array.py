import sys

def guess(x,y):
    return x * x <= y

def cal_data(n):
    left = 0
    right = n
    result = 0

    while left < right:
        mid = (left + right) / 2
        if guess(mid,n):
            left = mid + 1
            result = mid
        else:
            right = mid
    
    return result

def guess_array(x,res,ary):
    print "geuss:" + str(x)
    return ary[x] == res

def find_array(res,array):
    left = 0
    right = len(array)
    result = -1

    while left < right:
        mid = (left + right) / 2
        if guess_array(mid,res,array):
            left = mid + 1
            result = mid
        else:
            right = mid
    
    print result
    
    return result

if __name__ == '__main__':
    # for i in range(10,100,2):
    #     print str(i) + "=" + str(cal_data(i))
        
    array = [1,3,5,7,9,12,14,16,18,19,30,58,68]
    
    find_array(16,array)
    find_array(17,array)
    
    