# **Exploring Linguistic Diversity Within CT Public Schools**


## Introduction
After spending the last couple years focusing my Global Scholars research on global primary education equity, I knew I wanted to continue exploring education equity for this project, but now on a smaller scale. I initially searched for datasets that contained data for the entirety of the U.S.; however, I eventually narrowed down my search to just the state of Connecticut in order to provide for a more narrowed area of research, and thus more accurate data.

For this project, I explored three questions:

1. What percentgae of students in all of Connecticut are ELL students? What percentage are non-ELL students?
2. What is the most lingusitically diverse town in Connecticut? And what percentage of their students are ELL students? What are the least linguistically diverse towns in Connecticut? And what percentage of their students are ELL students?
3. What is the most linguistically diverse town in Connecticut ranked as in terms of education equity?


## Methods

### Datasets
The first dataset is produced by the Connecticut State Department, and it contains information about the number of enrolled students per year who have English language learner status, compared to the number who speak english natively, categorized by school district.

The second dataset is produced by the U.S. Census Bureau and the National Center for Education Statistics, and it contains a large majority of the Connecticut public school districts, ranked in terms of how equitable the distribution of funding is.

Both datasets can be used for independent research purposes because they are under the creative commons license.
 
### Process
For the first two questions, I only required data from the first dataset. To see what percentgae of students in all of Connecticut are ELL students, I iterated through all of the school districts (that had available data) and calculated the total of ELL students, non-ELL students, as well as the overall total of students.
To see which town in Connecticut is the most lingusitically diverse, and which ones are the least lingusitically diverse, I iterated through all of the towns, and calcualted the percentage of ELL students in each district, comparing the percentages to one another, and then printing out the largest percentgae for the most linguistically diverse district, and the smallest percentage for the least lingusitically diverse districts. I also printed the names of each town with the lowest percentage in an Array List.
For my last question, I used the data derived from my second question (what is the most lingusitically diverse town) and compared that town name to a rankd list of school districts in Connecticut to see how equitable the division of funding is, and printed out the districts rank.

### Challenges
I faced a few challenges throughout my research, primarily concerned with my datasets. First, the dataset containing ELL student data had empty values arbitrarily spread throughout the dataset, and thus some lines in the data returned empty values. To fix this, we implemented an if statement that would check that the line is not emtpy, brefore going through the line with a Scanenr.
Second, the dataset containing ELL student data had negative student values, which skewed my data, for I was getting negative total student populations for some districts. To fix this issue, in an if statement, I check that the number of students at the value index is a positive number, and if it is not, I skip that line in the file, and read the next one.


## Results and Discussion
The data exposed alarming trends within Connecticut public schools. Firsly, only 8% of Connecticut's public school student population is considered as ELL students, and the most diverse town is Windham School District (still with only 29% ELL students). Perhaps more alarming is that the most diverse district was ranked 147 out of 166 school districts in CT in terms of funding equity. This means that in a school district with a large ELL student population, that is more likely to be in need of extra assistance/funding, equity is amongst the lowest out of all school districts.

Of course, the derived data has its limitations. For example, some districts did not provide ELL student data,a nd thus were not included in calculation. Additionally, ELL student population as a percentage doesn't necessarily mean that a school district is in need of more equitable funding, it simply implies that there is a higher percentage chance that students will be at a disadvantage, compared to their native english speaking counterparts.

Next, I would like to explore how the districts with high ESL student populations score on standardized tests (particularly math sections), because multiple studies suggest that bilingualism positively affects other neurological aspects, such as problem solving, and it would be interesting to see if ELL student populations could provide a window into these studies.