# add vi-mode plugin for zsh
plugins=(
    vi-mode # for vim mode in zsh
)

# set cursor style in vim mode
function zle-keymap-select {
  if [[ ${KEYMAP} == vicmd ]]; then
    echo -ne "\e[2 q"  # NORMAL mode: block cursor
  else
    echo -ne "\e[6 q"  # INSERT mode: vertical line cursor
  fi
}

zle -N zle-keymap-select
