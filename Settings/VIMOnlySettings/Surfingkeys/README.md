# SurfingKeys Configuration

[SurfingKeys GitHub Repository](https://github.com/brookhong/Surfingkeys)

## Full keymap file

[Settings/VIMOnlySettings/Surfingkeys/Settings/surfingkeys.js](Settings/VIMOnlySettings/Surfingkeys/Settings/surfingkeys.js)

## Custom VIM-Style Key Bindings

1. **Remapped Navigation Keys** (aligned with VIM directional layout):
   ```javascript
   api.map("i", "k");  // Scroll up (original 'k' function)
   api.map("k", "j");  // Scroll down (original 'j' function)
   api.map("K", "d");  // Half-page down 
   api.map("I", "e");  // Half-page up
   ```

   Visual mode mappings preserve VIM's text selection logic:

   ```javascript
   api.vmap("i", "k");  // Expand selection upward
   api.vmap("k", "j");  // Expand selection downward
   ```

   ```text
           [i ↑ Scroll Up]
   [j ← Left] [k ↓ Scroll Down] [l → Right]
   ```

## Essential Productivity Features

1. **Tab Management**
   - `x` - Close current tab (VIM-style)  
   - `X` - Restore closed tab (reopens last closed window with full tab state)
   - `Shift+W` - Move current tab to new browser window