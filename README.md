# svg2png
Visual Paradigm 导出的 .svg 文件去掉水印后转成 png文件,水印可配置，配置文件（resources/config.properties） 

目录：
[1. 查询要修改的BOM行](#1)
## 1. 查询要修改的BOM行
- **参数**：BOM行号，BOM类型，工厂id，期望生效时间
- **返回值**：PartAssembly 对象自动填充到修改的行
- **逻辑**：根据参数查询出指定日期生效的BOM
2. 在前段修改工位和工位拆分字段传递到后端保存
- **参数**：PartAssembly 集合对象
- **返回值**：void
- **逻辑**：
