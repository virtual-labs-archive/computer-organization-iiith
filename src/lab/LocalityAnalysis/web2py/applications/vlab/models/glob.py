import time
import os
import re
import commands
glob={
		'code':'#include <stdlib.h>\n#define cnSize 64\nint A[cnSize][cnSize];\nint B[cnSize][cnSize];\nint C[cnSize][cnSize];\nint x[cnSize];\n\
int y[cnSize];\n\
int i, j, k;\n\
\n\
// fill matrix and vector with random values\n\
void randinit(){\n\
  srand ( time(NULL) );\n\
  for (i = 0; i < cnSize; ++i) {\n\
    for (j = 0; j < cnSize; ++j) {\n\
      A[i][j] = rand()%1000;\n\
      B[i][j] = rand()%1000;\n\
    }\n\
    x[i] = rand()%1000;\n\
  }\n\
}\n\
\n\
// matrix-vector multiplication\n\
void matvec(){\n\
  for (i = 0; i < cnSize; ++i){\n\
    y[i] = 0;\n\
    for (k = 0; k < cnSize; ++k){\n\
        y[i] += A[i][k] * x[k];\n\
    }\n\
  }\n\
}\n\
\n\
// matrix-matrix multiplication\n\
void matmult(){\n\
  for (i = 0; i < cnSize; ++i){\n\
    for (j = 0; j < cnSize; ++j){\n\
      C[i][j] = 0;\n\
      for (k = 0; k < cnSize; ++k){\n\
        C[i][j] += A[i][k] * B[k][j];\n\
      }\n\
    }\n\
  }\n\
}\n\
\n\
main(){\n\
  randinit();\n\
  matmult();\n\
}'
		}
