#include<stdio.h>

long trec(long n, long m) {
  if (n == 0) {
    return m;
  }
  return trec(n - 1, m + 1);
}

int main(int argc, char* argv[]) {
  printf("If there's a stack overflow, the following line should read \"Segmentation fault\"\n");
  printf("...But, there wasn't, because trec returned %ld\n", trec(1000000, 0));
  return 0;
}
