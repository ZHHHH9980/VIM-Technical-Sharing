// --------------------- common.js ---------------------
// 通用工具模块 (包含共享功能)

// 生成随机版本标识符 a
export const generateVersion = (prefix = "v") =>
  `${prefix}-${Math.random().toString(36).substr(2, 9)}`;

// 生成当前年份版权声明 b
export const getCurrentYear = () => new Date().getFullYear();
