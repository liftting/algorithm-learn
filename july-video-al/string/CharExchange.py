import sys
import io
# 计算0 1 串 如果要排序（0 在前 1 在后，需要交换的次数问题）
def exchange_char(array):
	if array == None or len(array) == 0:
		return
	
	print(array)
	
	i = 0
	j = len(array) - 1
	swap_count = 0
	while i < j:
		# 先查找，再交换
		while i < j and array[i] == 0:
			i+=1 # python cancel i++
		
		while i<j and array[j] == 1:
			j-=1
			
		if i < j:
			swap_count+=1
		i+=1
		j-=1
	
	print(swap_count)



if __name__ == '__main__':
	array = [0,1,1,0,1,0,1,0,1,0,1]
	exchange_char(array)
