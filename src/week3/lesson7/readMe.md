Java 代码递归模板
```
void recurse(level,param1,param1...){
   # recurse terminator 递归的终止条件
    if level> MAX_LEVEL
      process_result;
      return;
    # process logic in current level
    process(level ,data...); 
    # drill down 下探到下一层
    self.recursion(level+1,p1...)    
    # reverse the current level status if needed 清理当前层
}

public void recurse(int level, int param) { 

  // terminator 
  if (level > MAX_LEVEL) { 
    // process result 
    return; 
  } 

  // process current logic 
  process(level, param); 

  // drill down 
  recur( level: level + 1, newParam); 

  // restore current status 
 
}
```

思维要点
1、不要人肉递归（最大误区）
2、找到最近最简单的问题，将其拆解为可重复解决的问题（重复子问题）
3、数学归纳法的思维