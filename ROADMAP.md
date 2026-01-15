*When an event is triggered inside the toolbar or fileTreeArea, the first order of business MUST be to 
setCurrentLocation of the Brain object.

*After this update has been made to the code, the brain should not publish any hard-coded values; instead it should 
publish whatever it's current location is. The only exception to this rule should be when
"home" or "root" is triggered.

*The Properties area does not have any permissions to trigger any event.

*The Display area does not have any permissions to trigger any event *within* the application,
but it does have permission to ask the OS to open the targeted file when the user triggers an event inside of said
Display area.

*TODO: Focus on making directory names in FileTreeArea clickable.

*TODO: When user clicks on a directory name in FileTreeArea occurs, trigger a send of the DirectoryNameAsString to the brain.
Trigger update cycle with new DirectoryNameAsString.