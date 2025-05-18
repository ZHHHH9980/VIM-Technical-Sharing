# VIM-Enhanced Development Ecosystem

A curated collection of configuration files and productivity tools for creating a unified VIM-inspired workflow across development environments. Includes integrations for:

- 🖥 Terminal (ZSH/Tmux)
- 🖱 GUI Editors (VSCode)
- 🌐 Browsers (SurfingKeys extension)
- ⌨ Input Devices (Karabiner)

## Project Structure

```
.
├── Settings/
│   ├── MyCompleteSettings/         # Full-stack environment config
│   │   ├── .zshrc                 # Comprehensive ZSH setup
│   │   └── karabiner/            # Keyboard remapping configs
│   │
│   ├── VIMOnlySettings/          # VIM-specific optimizations
│   │   ├── .zshrc                # Shell integration for VIM workflows
│   │   ├── settings.json         # VSCode VIM extension configuration
│   │   └── surfingkeys.js        # Browser extension `Surfingkeys` settings
│   │
│   └── tmux.conf                 # Terminal multiplexer setup
│
└── VIMTechnologySharing/
    ├── DemoSrcCode/              # Practical implementation examples
    ├── 1.basicOperation.md       # Core VIM operations
    ├── 2.navigation.md           # Efficient movement patterns
    └── 3.plugins.md              # Ecosystem extensions guide
```

## Core Features

### Configuration Profiles

- **Full Environment (MyCompleteSettings)**

  - 🐚 ZSH shell with VIM bindings
  - ⌨ Karabiner key mapping profiles

- **VIM Optimization Suite**
  - 🖥 Tmux workspace management
  - 🌐 Browser extension `Surfingkeys` config
  - 🔌 VSCodeVIM integration

## Documentation Portal

Explore in `VIMTechnologySharing`:

- [Basic Operations](VIMTechnologySharing/1.basicOperation.md) - Essential command library
- [Navigation Systems](VIMTechnologySharing/2.navigation.md) - Advanced movement techniques
- [Plugin Ecosystem](VIMTechnologySharing/3.plugins.md) - Extension configuration deep-dive

## Key Integrations

1. **Karabiner** ⌨  
   Remap keys for ergonomic VIM usage:  
   `CapsLock ⇄ ESC` | Custom modifier layers

2. **Tmux** 🖥📑  
   Terminal multiplexing with VIM-style navigation:  
   `<ctrl-b> + [` → Enter scroll/copy mode

3. **SurfingKeys** 🌐🔑  
   Browser extension bringing VIM controls to web navigation:  
   `j/k` scrolling | `f` link hints | `o` command palette
