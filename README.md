# Vimified Cursor

This repository contains my personal configuration for a Vim-powered Cursor IDE setup. It demonstrates how to combine the efficiency of Vim with modern IDE features.

## File Structure

```
├── README.md
├── Settings
│   └── settings.json       # Cursor(VSCode) Vim Settings
└── CursorVimKeymapDesign  
   ├── 1.basicOperation.md  # Introduction to basic Vim operations
   ├── 2.navigation.md      # Introduction to Vim navigation
   └── 3.plugins.md         # Introduction to Vim plugins
└── DemoSrcCode             # Demo source code for blog posts (can be ignored)
```

## Try it on [CodeSandbox](https://codesandbox.io/p/sandbox/vim-playground-forked-vm6tfz?file=%2FREADME.md%3A24%2C1)

[Playground link](https://codesandbox.io/p/sandbox/vim-playground-forked-vm6tfz?file=%2FREADME.md%3A24%2C1)

1. Press Command + Shift + P
2. Type "settings.json"
3. Click on "Open User Settings (JSON)"

![image](https://github.com/ZHHHH9980/Vimified-Mordern-IDE/blob/master/assets/SettingsJson.jpg?raw=true)

4. Copy the content from [Settings/settings.json](https://github.com/ZHHHH9980/Vimified-Mordern-IDE/blob/master/Settings/settings.json) into the file
5. Save the file
6. Install the extensions "VSCodeVim" and "Which Key" (optional)

![image](https://github.com/ZHHHH9980/Vimified-Mordern-IDE/blob/master/assets/VScodeVimExtension.png?raw=true)

![image](https://github.com/ZHHHH9980/Vimified-Mordern-IDE/blob/master/assets/WhichKey.jpg?raw=true)

## Key Mapping

### 1. Normal mode

**Note: Most of The key mapping also support in Visual mode.**

#### 1.1 Move cursor

1. Move cursor
```
           [i ↑  Up]
[j ← Left] [k ↓ Down] [l → Right]
```

**Note:** These key mappings are more intuitive than the default ones. Insert mode is mapped to [h] since [h] is positioned to the left of [j], creating a logical correspondence for moving the cursor left.

**Tip: Vim commands are composed of three parts:** 

[count] + verb + object

where count (optional) specifies how many times the operation should be performed.

Recommended settings:

```
:set relativenumber # enable relative line number
```

With relative line numbers, you can jump multiple lines up/down at once. Combining the count with [i ↑  Up][k ↓ Down] is more efficient.

Like: 

```
10i ↑  -> 10 lines up
10k ↓  -> 10 lines down
```

2. Jump multiple lines up/down at once

Following the convention that uppercase keys provide enhanced motion (my personal preference):

```
K -> ['k','k','k','k','k','k','k','k','k','k','k']

I -> ['i','i','i','i','i','i','i','i','i','i','i']
```

#### 1.2 CRUD

1. Default register [a]

**All yank/delete/cut/paste operations use register [a], instead of default register ["]. Why?**

Because the default delete or cut operations put content into register ["], and the default paste operation pastes from register ["], which may have unintended side effects.

If you want to delete or cut to register [a], you can use [ndd] or [ncc], which will put the content into register [a].

```
ndd -> means you need to delete the content and put it into register [a]
```

2. Yank with multiple registers

- byiw -> yank inner word into register [b]
- cyiw -> yank inner word into register [c]
- dyiw -> yank inner word into register [d]
- ...

- bp -> paste from register [b]
- cp -> paste from register [c]
- dp -> paste from register [d]
- ...

3. Yank to system clipboard

With [Y], you can yank to the system clipboard, which enhances the default [y] that yanks to register [a].
