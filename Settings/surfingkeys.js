// an example to create a new mapping `ctrl-y`
api.mapkey("<ctrl-y>", "Show me the money", function () {
  Front.showPopup(
    "a well-known phrase uttered by characters in the 1996 film Jerry Maguire (Escape to close)."
  );
});

// an example to remove mapkey `Ctrl-i`
api.unmap("<ctrl-i>");

// 在 Google Docs 上禁用 SurfingKeys
settings.blocklistPattern = /https:\/\/docs\.google\.com\//;

// 将 i 重新映射为向上滚动
api.map("i", "k"); // 'e' 是 SurfingKeys 默认的向上滚动键

// 将 k 重新映射为向下滚动
api.map("k", "j");

api.map("K", "d"); // 'd' 是 SurfingKeys 默认的向下滚动键
api.map("I", "e"); // 'e' 是 SurfingKeys 默认的向上滚动键

// Frequently used mappings

// Shift + w
// move current Tab to a new window

// x
// close current Tab

// X
// restore last closed Tab
