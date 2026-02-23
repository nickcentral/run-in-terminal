# Run In Terminal (macOS) - IntelliJ Platform Plugin

<!-- Plugin description -->
Reuse the active macOS Terminal window to execute selected shell script lines directly from the editor.

### Platform Support
Available only on macOS.

### Features
- Execute a single-line at the caret position or the entire selection
- Automatically ignore commented lines
- Reuse the current Terminal session for continuous workflows

The plugin uses the macOS `osascript` command to send the selection to the active Terminal window or automatically launch a new one if none is available.  
It does not modify files or intercept terminal output.  
To continue using the same session, keep the desired Terminal window open and active.

### Get Started
- Open an .sh file or .md file containing a shell code block (shell, bash, zsh, sh).
- Place the caret on a line, or select multiple lines.
- Press Command + Enter.

Note: The selected content will be executed in the active macOS Terminal window (or a new one if none is active).

### Plugin Scope
The action is enabled when:
- Editing a .sh file
- Editing a .md file and the caret is inside an injected shell code block (shell, bash, zsh, sh)

### Default Shortcut
Command + Enter

### Feedback and Contributions
Suggestions, new usage scenarios, and contributions are welcome to help improve the plugin.
<!-- Plugin description end -->
