// Disable SurfingKeys on Google Docs
settings.blocklistPattern = /https:\/\/docs\.google\.com\//;

// Remap i to scroll up
api.map("i", "k"); // 'k' is SurfingKeys' default scroll up key

// Remap k to scroll down
api.map("k", "j");

api.map("K", "d"); // 'd' is SurfingKeys' default scroll down key
api.map("I", "e"); // 'e' is SurfingKeys' default scroll up key

api.vmap("i", "k"); // Press i to expand selection upward (original k function)
api.vmap("k", "j"); // Press k to expand selection downward (original j function)

// Shift + w
// move current Tab to a new window
