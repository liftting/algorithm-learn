import sys
def s_max(n):
    print "max int:" + str(sys.maxint)
    
    #0101 0101
    # 3 = 0011
    print str(n) + '=' + str(n&0x555555555)
    
    
    

if __name__ == '__main__':
    for i in range(1,11):
        s_max(pow(3,i))