// noinspection JSUnresolvedVariable,JSUnresolvedFunction

const buildJsExecutionError = (message) =>
    "Failed to execute JavaScript. Message: " + message;

function run(argv) {
  if (!argv || argv.length === 0) {
    throw new Error(buildJsExecutionError("No command provided"));
  }
  try {
    const cmd = argv[0];
    const terminal = Application("Terminal");
    if (terminal.windows.length === 0) {
      terminal.doScript(cmd);
    } else {
      terminal.doScript(cmd, {in: terminal.windows[0]});
    }
  } catch (e) {
    throw new Error(buildJsExecutionError(e.message));
  }
}
