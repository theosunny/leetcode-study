## 本周主要学习内容是
1.dfs： 深度优先搜索，（递归和迭代都可以实现，迭代其实就是借用外部空间模拟栈），个人喜好按照递归实现，这样代码的简洁性和易读性会很好，所以递归的思想需要牢记和练习。

2.bfs：个人理解就是像水波或者蛋糕一样一层层得解开，进入到下一层。每次处理当前层的元素，处理完成后进入下一层，比如二叉树的层序遍历是很好的练习bfs的例子。

3.贪心算法：贪心算法最难之处其实在于如何去找到贪心的规则，以及如何证明，其他的都是次要的，个人认为其实贪心算法能够解决的问题其实动态规划都能够解决，主要还是最优的思想，还是需要多多的习题练习。

4.二分查找：  二分查找原则上没什么难度，很简单，主要会熟悉代码模板，在后续的快速排序及分治都能看到二分查找思想的影子.主要是对二分查找思想的理解，形成肌肉记忆。
### dfs（深度优先）
    ```
     递归写法
     visited = set() 
     
     def dfs(node, visited):
         if node in visited: # terminator
         	# already visited 
         	return 
     
     	visited.add(node) 
     
     	# process current node here. 
     	...
     	for next_node in node.children(): 
     		if next_node not in visited: 
     			dfs(next_node, visited)
     
     
     非递归写法
     def DFS(self, tree): 
     
     	if tree.root is None: 
     		return [] 
     
     	visited, stack = [], [tree.root]
     
     	while stack: 
     		node = stack.pop() 
     		visited.add(node)
     
     		process (node) 
     		nodes = generate_related_nodes(node) 
     		stack.push(nodes) 
     
     	# other processing work 
     	...  
    ```    
### bfs（广度优先）
    def BFS(graph, start, end):
        visited = set()
    	queue = [] 
    	queue.append([start]) 
    
    	while queue: 
    		node = queue.pop() 
    		visited.add(node)
    
    		process(node) 
    		nodes = generate_related_nodes(node) 
    		queue.push(nodes)
    
    	# other processing work 
    	...
### 贪心算法

  是解决最优问题，和动态规划不同，是不会回退的.
  个人理解：贪心算法最难之处其实在于如何去找到贪心的规则，以及如何证明，其他的都是次要的。
### 二分查找
   二分查找原则上没什么难度，很简单，主要会熟悉代码模板，在后续的快速排序及分治都能看到二分查找思想的影子
   #### 二分查找的前提
    1. 目标函数单调性（单调递增或者递减）
    2. 存在上下界（bounded）
    3. 能够通过索引访问（index accessible)  
    代码模板：
        left, right = 0, len(array) - 1
        while left <= right:
        mid = (left + right) / 2
        if array[mid] == target:
        # find the target!!
        break or return result
        elif array[mid] < target:
        left = mid + 1
        else:
        right = mid - 1
