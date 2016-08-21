# 494	Kindergarten Counting Game

https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=435

### Tips and Tricks:
1. Numbers or anything between letters are considered as space. That is the following string contains 2 words: `asf12312312asfsd`
2. If you are using split (using regex), it counts an empty string before space or anything before a word in the string (at the beginning of the string). That is it counts two words in this string `23413 safdasfds`.
So you have to subtract that from the count of words found using split.
3. A good regex for getting any string of characters other than alphabets: ([^a-zA-Z])+ -> where ^ indicates "not"