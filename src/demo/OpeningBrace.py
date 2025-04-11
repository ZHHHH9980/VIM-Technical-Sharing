// v + }
// TODO: Delete func1 to func2
def func1():
    """第一个函数"""
    print("Hello from func1!")
    return 42


def func2():
    """第二个函数"""
    print("Hello from func2!")
    return 100


def func3():
    """第三个函数"""
    print("Hello from func3!")
    return 999



def func4():
    """第四个函数"""
    print("Hello from func4!")
    return 0


# 保留
x = 10
y = 20
print(x + y)


// delete inside indent
def process_data(data, threshold=0.5, verbose=False):
    """
    处理多层嵌套数据的复杂函数
    参数:
        data: 可以是字典、列表或混合结构
        threshold: 过滤阈值
        verbose: 是否打印详细日志
    返回:
        处理后的数据结构和统计信息
    """
    results = {
        'passed': [],
        'failed': [],
        'stats': {
            'total': 0,
            'valid': 0,
            'invalid': 0
        }
    }

    if not isinstance(data, (dict, list)):
        if verbose:
            print(f"[WARNING] 无效输入类型: {type(data)}")
        return None

    def _validate_item(item):
        """嵌套的验证函数"""
        if isinstance(item, dict):
            if 'value' not in item:
                if verbose:
                    print("[DEBUG] 缺失value字段")
                return False
            return item['value'] >= threshold
        elif isinstance(item, (int, float)):
            return item >= threshold
        return False

    for i, element in enumerate(data):
        try:
            # 第一级处理
            if isinstance(element, list):
                sub_results = []
                for sub_elem in element:
                    # 第二级处理
                    if _validate_item(sub_elem):
                        sub_results.append(sub_elem)
                        results['stats']['valid'] += 1
                    else:
                        if verbose:
                            print(f"[LOG] 丢弃子项: {sub_elem}")
                        results['stats']['invalid'] += 1
                
                if sub_results:
                    results['passed'].append({
                        'index': i,
                        'data': sub_results
                    })
            
            # 字典类型的处理
            elif isinstance(element, dict):
                if _validate_item(element):
                    results['passed'].append(element)
                    results['stats']['valid'] += 1
                else:
                    results['failed'].append(element)
                    results['stats']['invalid'] += 1
            
            results['stats']['total'] += 1

        except Exception as e:
            print(f"[ERROR] 处理索引 {i} 时出错: {str(e)}")
            continue

    # 最终结果处理
    if not results['passed']:
        if verbose:
            print("[WARNING] 没有通过的有效数据")
        return None
    
    return {
        'summary': f"共处理 {results['stats']['total']} 条数据",
        'details': results
    }