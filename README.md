# Eoghan-Ryan---sba23314---CA1

Repo for CA1 OOP Module @ CCT

This is the readme for my CA1 Repo for OOP class @ CCT.

When in operation, the program will be given a file named “students.txt” – this contains the details of (fictitious) students in the following format:

Line 1 – <First Name> <Second Name>
Line 2 – <Number of classes>
Line 3 – <Student number>

My goal is to read the data from the file and check that it is valid. With the following to be checked;

1. the first name must be letters only;

2. The second name can be letters and/or numbers and must be separated from the first name by a single space;

3. the number of classes must be an integer value between 1 and 8 (inclusive), and

4. The student “number” must be a minimum of 6 characters with the first 2 characters being numbers, the 3rd and 4th characters (and possibly 5th ) being a letter, and everything after the last letter character being a number.

If the data is not valid, my goal is to output an error message on the screen to the user.

If the data IS valid, I have to output the data to a file named status.txt, in the following format;

<Student number> - <Second Name>
<Workload>

Where the <Workload> is determined by the number of classes, as follows:

1 Very Light
2 Light
3, 4, or 5 Part Time
6 or higher Full Time

1. Ensure that the student number year is at least 2020 (i.e. that the number starts with 20 or higher)
2. Ensure that the number after the letter(s) is reasonable – i.e. that it is between 1 and 200
3. The program has a menu that lets the user decide between standard operation or adding (validated) data to the status.txt file via the console. (Invalid data should NOT be saved).
