# VIM

## 1. Normal mode

### 1.0 Basic operation (语义化操作)

           [i ↑  Up]

[j ← Left] [k ↓ Down] [l → Right]

p -> paste

d -> delete

c -> cut

f -> find

y -> yank

g -> go

o -> open (a new line)

u -> undo

#### 1.0.0（Operator doubling）

yy -> yank a line

dd -> delete a link

cc -> cut a line(and change to the insert mode)

gg -> go to the first line

#### 1.0.1 Combine with {{count}}

1. relative line number with i/j

{{n}} + i/j

2. absolute line number with gg

{{n}} + gg

3. delete/yank/cut {{count}} line
   // 30k
   // F
   // C

{{n}} dd/yy/cc

#### 1.0.2 The Uppercase to reverse the action

find: <- F f ->

paste: <- P p ->

go to the last/first line: G/gg

open a new line: o/O

#### 1.0.3 Combine with Text Object

1. yank/delete/cut inner word (yiw/diw/ciw)

2. yank/delete/cut inner surrounding character

```
y/d/c + '       ->      'hello world'
y/d/c + `       ->      `hello world`
y/d/c + "       ->      "hello world"
y/d/c + ()      ->      ( hello world )
y/d/c + []      ->      [ hello world ]
y/d/c + <       ->      <div/>

y/d/c + {}      ->      { hello world }
// @TodoCalendarService.java
```

### 1.1 Operation with Plugins

Supercharge your workflow!

#### 1.1.0 The <leader> key

Prefix key for plugins

### 1.1.1 「Easymotion」 plugin

```
// s(search){char}
<leader>s{char}
```

如果是在大屏幕，能够容纳超多行的 code file，这个插件的效果会更好

### 1.1.2 「Vim Surround」 plugin

```
String
```

### 1.1.3 Replace with register

#### 1.1.2.0 VIM Registers

```
:reg

1. **无名寄存器** `"`：默认存储删除/复制内容
```

#### 1.1.2.1 Mutiple operations with registers

```
byiw -> yank inner word into {b} register
cyiw -> yank inner word into {c} register

bp   -> paste {b} register
cp   -> paste {c} register
```

#### 1.1.2.2 「ReplaceWithRegister」 plugin
