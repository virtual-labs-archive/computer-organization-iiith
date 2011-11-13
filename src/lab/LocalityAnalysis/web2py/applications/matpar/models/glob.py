import os
import re
import commands
import math

upload_path='/home/cvest.cso/public_html/LocalityAnalysis/web2py/applications/matpar/uploads/'
glob={'code':'for(ii = 0; ii < n;ii = ii+B)\nfor(jj = 0;jj<n;jj=jj+B)\nfor(kk=0;kk<n;kk=kk+B)\nfor(i=ii;i<ii+B;i++)\nfor(j=jj;j<jj+B;j++)\nfor(k=kk;k<kk+B;k++)\nz[i][j]=z[i][j]+x[i][k]*y[k][j];'}
		
