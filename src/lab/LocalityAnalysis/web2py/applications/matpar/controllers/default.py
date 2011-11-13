	
#########################################################################
## This is a samples controller
## - index is the default action of any application
## - user is required for authentication and authorization
## - download is for downloading files uploaded in the db (does streaming)
## - call exposes all registered services (none by default)
#########################################################################  
def register():
	return dict(form=auth.register(next=URL(request.application,'default','create_count')))

def create_count():
	user_id=auth.user.id
	db.p_count.insert(user_id=user_id,count=0)
	redirect(URL(request.application,'default','index'))

@auth.requires_login()
def index():
    """
    example action using the internationalization operator T and flash
    rendered by views/default/index.html or views/generic.html
    """
    last=request.vars

    #ok...so save the last query
    if last:
	 db.programs.i1_size.default=last.i1_size
   	 db.programs.i1_associativity.default=last.i1_associativity
	 session.i1_associativity=last.i1_associativity
   	 db.programs.i1_line_size.default=last.i1_line_size
	 session.i1_line_size=last.i1_line_size
	 db.programs.d1_size.default=last.d1_size
   	 db.programs.d1_associativity.default=last.d1_associativity
	 session.d1_associativity=last.d1_associativity
   	 db.programs.d1_line_size.default=last.d1_line_size
	 session.d1_line_size=last.d1_line_size
	 db.programs.l2_size.default=last.l2_size
   	 db.programs.l2_associativity.default=last.l2_associativity
	 session.l2_associativity=last.l2_associativity
   	 db.programs.l2_line_size.default=last.l2_line_size
	 session.l2_line_size=last.l2_line_size
	 session.n=last.n
	 db.programs.n.default=last.n
	 power=math.log(float(last.n),2)
	 
	 if int(last.n) > 256:
		 session.flash="Matrix size exceeded 256"
		 redirect(URL(r=request,f='index'))
	 if power - int(power) != 0:
		 request.vars=None
		 session.flash="matrix size must be power of 2"
		 redirect(URL(r=request,f='index'))
	 
	 session.B=last.B
	 session.cache_arch_id=last.id
	 db.programs.B.default=last.B
	 power1=math.log(float(last.B),2)
	 
	 if power1 - int(power1) !=0 :
		 request.vars=None
		 session.flash="Tile size must be power of 2"
		 redirect(URL(r=request,f='index'))
 	 if power1>power:
		request.vars=None
		session.flash="Tile size must be smaller than Matrix size"
		redirect(URL(r=request,f='index'))
    #it may be his first time!!
    else:
   	 db.programs.i1_size.default=2
   	 db.programs.i1_associativity.default=2
   	 db.programs.i1_line_size.default=64
   	 db.programs.d1_size.default=2
   	 db.programs.d1_associativity.default=2
   	 db.programs.d1_line_size.default=64
   	 db.programs.l2_size.default=8
   	 db.programs.l2_associativity.default=8
   	 db.programs.l2_line_size.default=64
    curr_count=db(db.p_count.user_id==auth.user.id).select()

    pid=[]
    values={}
    flag=0
    compile=True
    list=[]
    out="Test"
    inp=None
    out_mod=None
    input_path=""; i1_s=0
    form=SQLFORM(db.programs,dbio=False)
    if(form.accepts(request.vars,session)):
	 session.i1_ass=form.vars.i1_associativity
         flag=1

	 #take global parameter upload_path
	 path=upload_path
	 i1_s=form.vars.i1_size * 1024
	 session.i1_s=i1_s
	 session.i1_size=form.vars.i1_size
	 d1_s=form.vars.d1_size * 1024 
	 session.d1_s=d1_s
	 session.d1_size=form.vars.d1_size
	 l2_s=form.vars.l2_size * 1024 
	 session.l2_s=l2_s
	 session.l2_size=form.vars.l2_size
	 values["compile"]=compile; values["input"]=""
	 if compile==True:
          command='valgrind --tool=cachegrind --I1='+str(i1_s)+','+str(form.vars.i1_associativity)+','+str(form.vars.i1_line_size)+' '+'--D1='+str(d1_s)+','+str(form.vars.d1_associativity)+','+str(form.vars.d1_line_size)+' '+'--L2='+str(l2_s)+','+str(form.vars.l2_associativity)+','+str(form.vars.l2_line_size)+ ' --cachegrind-out-file='+path+'1'+'.'+str(curr_count[0].count)+'.%p '+'/home/cvest.cso/public_html/LocalityAnalysis/web2py/applications/matpar/static/test '+ str(form.vars.n)+' '+str(form.vars.B)
         
	  out=commands.getstatusoutput(command)
	
	  f=open('te',"w")
	  f.write(out[1])
	  f.close()
	  out_mod=re.split('\n',out[1])
	  regex="==(\d*)==\s*([^:]*):\s*(\d+.*)"
	  for i in out_mod:
		 match=re.findall(regex,i)
		 if len(match)>0:
		 	procid=match[0][0]
			if procid not in pid:
				 pid.append(procid)
			list.append(match[0])	
    if len(list)>0:
	regex="([^\(]+)\(\s*(\S+)[^\+]*\+\s*(\S+).*" ; t1= re.findall(regex,list[5][2]) 
	values["a"]=t1[0][0] ; values["a_1"]=t1[0][1] ; values["a_2"] = t1[0][2] 
	t2= re.findall(regex,list[6][2]) ; values["c"]=t2[0][0] ; values["c_1"]=t2[0][1] 
	values["c_2"] = t2[0][2] ; values["e"]=values["c"] ; values["e_1"] = values["c_1"]; values["e_2"]=values["c_2"] 
	t3= re.findall(regex,list[8][2]) ; values["d"]=t3[0][0] 
	values["d_1"]=t3[0][1] ; values["d_2"] = t3[0][2] 
	t4= re.findall(regex,list[7][2]) ; values["g"]=t4[0][0] 
	values["g_1"]=t4[0][1] ; values["g_2"] = t4[0][2]
	values["b"]= int(values["a"].replace(",",""))-int(values["c"].replace(",","")) 
	values["b_1"]= int(values["a_1"].replace(",",""))-int(values["c_1"].replace(",","")) 
	values["b_2"]= int(values["a_2"].replace(",",""))-int(values["c_2"].replace(",","")) 
	values["f"]= int(values["e"].replace(",",""))-int(values["g"].replace(",","")) 
	values["f_1"]= int(values["e_1"].replace(",",""))-int(values["g_1"].replace(",","")) ; values["f_2"]= int(values["e_2"].replace(",",""))-int(values["g_2"].replace(",","")) 
	values["h"]=str("%.2f"%((float(values["g"].replace(",",""))/float(values["e"].replace(",","")))*100))+"%" 
	values["h_1"]=str("%.2f"%((float(values["g_1"].replace(",",""))/float(values["e_1"].replace(",","")))*100))+"%"
	values["h_2"]=str("%.2f"%((float(values["g_2"].replace(",",""))/float(values["e_2"].replace(",","")))*100))+"%"

#update count table on successful run...ensure updation on deletion too-to be done	
	
	curr_count=db(db.p_count.user_id==auth.user.id).select()
	curr_count=curr_count[0].count+1
	db(db.p_count.user_id==auth.user.id).update(count=curr_count)
    
    session.compile=compile
    code=glob['code']
    default_code=re.split('\n',code)
    
    return dict(default_code=default_code,form=form,message=T('Hello World'),list=list,pid=pid,flag=flag,values=values,compile=compile)
	
def show_session():

	return dict(session=session)

def show_graphs():
	 list=[]
	 y1=[]
	 y2=[]
	 y3=[]
	 n=int(session.n)
	 B=int(session.B)
	 xticks='['
         path=upload_path
	 if session.compile==True:
	    
	    rows=db((db.graph_cache.i1_size==session.i1_size)&(db.graph_cache.i1_associativity==session.i1_associativity)&(db.graph_cache.i1_line_size==session.i1_line_size)&(db.graph_cache.d1_size==session.d1_size)&(db.graph_cache.d1_associativity==session.d1_associativity)&(db.graph_cache.d1_line_size==session.d1_line_size)&(db.graph_cache.l2_size==session.l2_size)&(db.graph_cache.l2_associativity==session.l2_associativity)&(db.graph_cache.l2_line_size==session.l2_line_size)&(db.graph_cache.n==n)).select()
	    if len(rows)>0:
	       ans1=rows[0].ans1
	       ans2=rows[0].ans2
	       ans3=rows[0].ans3
	       ans=rows[0].ans
	       xticks=rows[0].xticks
	    if len(rows)==0:
		for i in range(1,n+1):
		     if n%i==0: 
			if i!=n:
			   xticks+=str(int(i))+','
			if i==n:
			   xticks+=str(int(i))+']'
			list=[]
          		command='valgrind --tool=cachegrind --I1='+str(session.i1_s)+','+str(session.i1_associativity)+','+str(session.i1_line_size)+' '+'--D1='+str(session.d1_s)+','+str(session.d1_associativity)+','+str(session.d1_line_size)+' '+'--L2='+str(session.l2_s)+','+str(session.l2_associativity)+','+str(session.l2_line_size)+ ' --cachegrind-out-file='+path+str(auth.user.id)+'.'+'1'+'.%p '+'/home/cvest.cso/public_html/LocalityAnalysis/web2py/applications/matpar/static/test '+ str(session.n)+' '+str(i)
			out=commands.getstatusoutput(command)
			out_mod=re.split('\n',out[1])
	  		regex="==(\d*)==\s*([^:]*):\s*(\d+.*)"
	  		for j in out_mod:
		 		match=re.findall(regex,j)
		 		if len(match)>0:
					list.append(match[0])
			if len(list) >0:
				
				y1.append(100 - float(re.findall('[^%]+',(list[3][2]))[0]))
				regex="([^\(]+)\(\s*(\S+)[^\+]*\+\s*(\S+).*"
				t3= re.findall(regex,list[8][2])
				y2.append(100 -float(re.findall('[^%]+',t3[0][0])[0]))
				l2i_miss=int(list[2][2].replace(",",""))
				l2d_miss= re.findall(regex,list[7][2])[0][0]
				l2_miss=l2i_miss+int(l2d_miss.replace(",",""))
				l2i_ref=int(list[1][2].replace(',',''))
				l2d_ref=re.findall(regex,list[6][2])[0][0]
				l2_ref=l2i_ref+int(l2d_ref.replace(',',''))
				l2_miss_rate=((float(l2_miss)/l2_ref)*100)
				y3.append(100 - l2_miss_rate)
		
		ctr=0
		ans1="[["
		for i in range(1,n+1):
		   if n%i==0 :
			ans1=ans1+'['+str(i)+','+str(y1[ctr])+']'
			if i!=n:
				ans1+=','
			ctr+=1
		ans1+="]]"
		ans2="[["
		ctr=0
		for i in range(1,n+1):
		   if n%i==0:
			ans2=ans2+'['+str(i)+','+str(y2[ctr])+']'
			if i!=n:
				ans2+=','
			ctr+=1
		ans2+=']]'
		ans3="[["
		ctr=0
		for i in range(1,n+1):
		    if n%i==0 :	
			ans3=ans3+'['+str(i)+','+str(y3[ctr])+']'
			if i!=n:
				ans3+=','
			ctr+=1
		ans3+=']]'
		ctr=0
		ans="[["
		for i in range(1,n+1):
		    if n%i==0:	
			ans=ans+'['+str(i)+','+str(y1[ctr])+']'
			if i!=n:
				ans+=','
			ctr+=1
		ans+='],'
		ans+="["
		ctr=0
		for i in range(1,n+1):
		    if n%i==0:
			ans=ans+'['+str(i)+','+str(y2[ctr])+']'
			if i!=n:
				ans+=','
			ctr+=1
		ans+='],'
		ans+="["
		ctr=0
		for i in range(1,n+1):
		    if n%i==0:
			ans=ans+'['+str(i)+','+str(y3[ctr])+']'
			if i!=n:
				ans+=','
			ctr+=1
		ans+=']]'
	  	db.graph_cache.insert(i1_size=session.i1_size,i1_associativity=session.i1_associativity,i1_line_size=session.i1_line_size,d1_size=session.d1_size,d1_associativity=session.d1_associativity,d1_line_size=session.d1_line_size,l2_size=session.l2_size,l2_associativity=session.l2_associativity,l2_line_size=session.l2_line_size,n=n,ans1=ans1,ans2=ans2,ans3=ans3,ans=ans,xticks=xticks)
         
	 return dict(ans1=ans1,ans2=ans2,ans3=ans3,ans=ans,y1=y1,xticks=xticks)

def customize():
    form=SQLFORM(db.options)
    
def user():
    """
    exposes:
    http://..../[app]/default/user/login 
    http://..../[app]/default/user/logout
    http://..../[app]/default/user/register
    http://..../[app]/default/user/profile
    http://..../[app]/default/user/retrieve_password
    http://..../[app]/default/user/change_password
    use @auth.requires_login()
        @auth.requires_membership('group name')
        @auth.requires_permission('read','table name',record_id)
    to decorate functions that need access control
    """
    if request.args(0)=='register':
	    redirect(URL(request.application,'default','register'))
    return dict(form=auth())


def download():
    """
    allows downloading of uploaded files
    http://..../[app]/default/download/[filename]
    """
   
    return response.stream(open(os.path.join(request.folder,'uploads',request.args[0]),'rb'))
    #return response.download(request,db)


def call():
    """
    exposes services. for example:
    http://..../[app]/default/call/jsonrpc
    decorate with @services.jsonrpc the functions to expose
    supports xml, json, xmlrpc, jsonrpc, amfrpc, rss, csv
    """
    session.forget()
    return service()
