#include <stdio.h>
#include <stdlib.h>

double findavg(double **data, int rows, int columns)
{
	int n ,i;
	double time, sum, average;
	for(n = 0; n < rows; n++)
	{
		for(i = 0; i < columns; i++)
		{
			sum = sum + data[n][i];
			time++;
		}
	}
	average = sum/time;
	return(average);
}

double findcor(double **data, int rows, int columns)
{
	double sum;
	sum = data[0][0] + data[0][columns-1] + data[rows-1][0] + data[rows-1][columns-1];
	return(sum);
}

double allrow(double **data, int newrow, int columns)
{
	int n;
	double sum=0;
	for(n=0; n<columns; n++)
		sum = sum + data[newrow][n];
	return(sum);
}

double allcolumn(double **data, int rows, int newcolumn)
{
	int n;
	double sum=0;
	for(n=0; n<rows; n++)
		sum = sum + data[n][newcolumn];
	return(sum);
}

double alldia(double **data, int rows, int columns)
{
	int n;
	double sum=0;
	for(n=0; n<rows; n++)
		sum = sum + data[n][n];
	return(sum);
}

double findb(double **data, int rows, int columns, double corner)
{
	int n;
	double sum = 0;
	for (n=0; n<rows; n++)
		sum = sum + data[n][0] + data[n][columns-1];
	for (n=0; n<columns; n++)
		sum = sum + data[0][n] + data[rows-1][n];
	sum = sum - corner;
	return (sum);
}

int

main(void)
{

	int n, i, rows, columns, newrow, newcolumn;
	double avg, corner, rowsum, columnsum, diag, bord;
	double **data;
	FILE *in;

	in = fopen("data.txt", "r");

	fscanf(in, "%d %d", &rows, &columns);
	printf("The rows and columns are %d and %d\n", rows, columns);
	
	data = (double**) calloc (rows, sizeof(double*));
	for(n=0; n<rows; n++)
		data[n] = (double*) calloc (columns, sizeof(double));
	

	for(n = 0; n < rows; n++)
	{
		for(i = 0; i < columns; i++)
		{
			fscanf(in, "%lf", &data[n][i]);
			printf("  %.4lf  ", data[n][i]);
		}
		printf("\n");
	}
	
	avg = findavg(data, rows, columns);
	printf("\nThe avg is %.4lf", avg);

	corner = findcor(data, rows, columns);
	printf("\nThe sum of four corners is %.4lf", corner);
	
	
	fscanf(in, "%d", &newrow);
	printf("\nThe value of row from the file is: %d", newrow);
	rowsum = allrow(data, newrow, columns);
	printf("\nThe sum of this row is %.4lf", rowsum);
	
	fscanf(in, "%d", &newcolumn);
	printf("\nThe value of column from the file is: %d", newcolumn);
	columnsum = allcolumn(data, rows, newcolumn);
	printf("\nThe sum of this column is %.4lf", columnsum);
	
	bord = findb(data, rows, columns, corner);
	printf("\nThe sum of borders is %.4lf", bord);

	if(rows == columns)
	{
		diag = alldia(data, rows, columns);
		printf("\nThe sum of diag is %.4lf", diag);
	}
	
	fclose(in);
	
	return(0);
}