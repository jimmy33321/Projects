#include <stdio.h>

int main() {

int c;
int w;
while ((c = getchar()) != EOF) {
if (c == ' ')
{
w += 1;
}

}

return w;
}
