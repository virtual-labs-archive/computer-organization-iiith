#include<stdio.h>
#include<stdlib.h>

int main(int argc, char * argv[])
{
	srand(1);
	int B=atoi(argv[2]),ii,jj,kk,i,j,k,n=atoi(argv[1]),**x,**y,**z;
	x=(int **)calloc(n,sizeof(int *));
	y=(int **)calloc(n,sizeof(int *));
	z=(int **)calloc(n,sizeof(int *));
	
	for(i=0;i<n;i++)
	{
		x[i]=(int *)calloc(n,sizeof(int));
		y[i]=(int *)calloc(n,sizeof(int));
		z[i]=(int *)calloc(n,sizeof(int));
		for(j=0;j<n;j++)
		{
			x[i][j]=rand()%1000;
			y[i][j]=rand()%1000;
		}
	}
	for(ii=0;ii<n;ii=ii+B)
		for(jj=0;jj<n;jj=jj+B)
			for(kk=0;kk<n;kk=kk+B)
				for(i=ii;i<ii+B;i++)
					for(j=jj;j<jj+B;j++)
						for(k=kk;k<kk+B;k++)
							z[i][j]=z[i][j]+x[i][k]*y[k][j];
/*	for(i=0;i<n;i++){
		for(j=0;j<n;j++)
			printf("%d ",z[i][j]);
		printf("\n");
	}*/
	return 0;
}
