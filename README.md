# mp-sorting-maven

An exploration of sorting in Java.

Authors

* Bonsen Yusuf
* Samuel A. Rebelsky (starter code)

Acknowledgements

* https://rebelsky.cs.grinnell.edu/Courses/CSC207/2024Fa/readings/sorting.html
* https://rebelsky.cs.grinnell.edu/Courses/CSC207/2024Fa/readings/mergesort.html
* https://rebelsky.cs.grinnell.edu/Courses/CSC207/2024Fa/readings/quicksort.html
* https://rebelsky.cs.grinnell.edu/Courses/CSC207/2024Fa/eboards/eboard18-2.html
* ChatGPT (see below)

This code may be found at <https://github.com/yusufbon/mp-sorting-maven>. The original code may be found at <https://github.com/Grinnell-CSC207/mp-sorting-maven>.

Description of custom sorting algorithm 
---------------------------------------
A custom sorting algorithm that dynamically chooses between sorting strategies based on input size and pre-sortedness. This algorithm uses Merge Sort for large arrays and switches to Insertion Sort for smaller subarrays, improving performance on nearly sorted data.

Notes on using Copilot (or other AI)
------------------------------------
Initially, I described the task at hand to GPT, and also described my already implemented algorithms. I asked GPT to provide me with some ideas to make my own algorithm, keeping in mind that it should be more "effective" than the rest. GPT suggested I could make a hybrid algorithm, and since that was also kind of hinted in the assingment itself ("You might pre-scan the input to look for patterns that suggest a particular algorithm, you might switch algorithms for small enough arrays, you might find something else clever to do."), I decided to go with that idea. My initial implementation wasn't compiling correctly, so GPT suggested I break down my code and add a seperate helper method that "merges" the algoritm, so then I implemented hybridSort(T[] values, int low, int high).
