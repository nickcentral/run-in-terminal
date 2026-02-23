# Run In Terminal (macOS) - IntelliJ Platform Plugin

<!-- Plugin description -->
<p>
  Reuse the active macOS Terminal window to execute selected shell script lines directly from the editor,
  preserving the same working directory, environment variables, and command history.
</p>

<h3>Platform Support</h3>
<p>Available only on macOS.</p>

<h3>Features</h3>
<ul>
  <li>Execute a single-line at the caret position or the entire selection</li>
  <li>Automatically ignore commented lines</li>
  <li>Reuse the current Terminal session for continuous workflows</li>
</ul>

<p><strong>Note:</strong> The plugin uses the macOS <code>osascript</code> command to send the selection to the active Terminal window or automatically launch a new one if none is available.<br>
It does not modify files or intercept terminal output.<br>
To continue using the same session, keep the desired Terminal window open and active.</p>

<h3>Plugin Scope</h3>
<p>The action is enabled when:</p>
<ul>
  <li>Editing a <code>.sh</code> file</li>
  <li>
    Editing a <code>.md</code> file and the caret is inside an injected shell code block
    (<code>shell</code>, <code>bash</code>, <code>zsh</code>, <code>sh</code>)
  </li>
</ul>

<h3>Default Shortcut</h3>
<p>Command + Enter</p>

<h3>Feedback and Contributions</h3>
<p>
  Suggestions, new usage scenarios, and contributions are welcome to help improve the plugin.
</p>
<!-- Plugin description end -->
