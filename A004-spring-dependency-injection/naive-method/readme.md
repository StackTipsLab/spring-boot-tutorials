## The issue
- This creates a hard-coded dependency. The creation and usage of dependencies are tightly intertwined.
- This can make our code difficult to test and maintain because we need to change the code whenever we change the dependencies.
- Multiple instance on heap, leads to memory issues.