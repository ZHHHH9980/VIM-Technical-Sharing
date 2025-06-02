# Vimified Modern IDE

A modern development environment that brings Vim's efficiency to contemporary tools. This repository contains both configuration files for a Vim-powered development setup and comprehensive documentation on mastering Vim-style workflows.

## Repository Structure

```
.
├── config/                        # All configuration files
│   ├── ide/                      # IDE-specific configurations
│   │   ├── cursor/              # Cursor IDE settings
│   │   │   └── settings.json    # Vim keybindings and preferences
│   │   └── vscode/             # VSCode configurations (if needed)
│   │
│   ├── terminal/                # Terminal environment setup
│   │   ├── zsh/                # ZSH configurations
│   │   │   ├── .zshrc         # Main ZSH configuration
│   │   │   └── aliases.zsh    # Custom aliases
│   │   └── tmux/              # Terminal multiplexer
│   │       └── tmux.conf      # Tmux configuration
│   │
│   ├── keyboard/               # Keyboard customization
│   │   └── karabiner/         # Karabiner configurations
│   │
│   └── browser/                # Browser Vim integration
│       └── surfingkeys.js      # SurfingKeys extension config
│
├── docs/                         # Documentation and guides
│   ├── getting-started/         # Setup and installation guides
│   │   ├── installation.md     # Installation instructions
│   │   └── quick-start.md      # Basic usage guide
│   │
│   ├── tutorials/               # Learning materials
│   │   ├── basic-operations.md # Core Vim operations
│   │   ├── navigation.md       # Movement patterns
│   │   └── plugins.md         # Plugin ecosystem guide
│   │
│   └── examples/               # Example code and use cases
│       └── demo/              # Demonstration code
│
└── scripts/                     # Installation and setup scripts
    ├── install.sh              # Main installation script
    └── setup/                  # Setup utilities
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
