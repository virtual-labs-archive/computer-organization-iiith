# -*- coding: utf-8 -*- 

#########################################################################
## This is a samples controller
## - index is the default action of any application
## - user is required for authentication and authorization
## - download is for downloading files uploaded in the db (does streaming)
## - call exposes all registered services (none by default)
#########################################################################  

@auth.requires_login()
def index():
    """
    example action using the internationalization operator T and flash
    rendered by views/default/index.html or views/generic.html
    """
    form=form_factory(Field('k','integer',requires=IS_LENGTH(3,1)))
    if form.accepts(request.vars,session):
	    session.k=request.vars.k
	    redirect(URL(request.application,'default','calc_add'))
    response.flash = T('Welcome to Teach me Binary')
    return dict(message=T('A tool for CSO Course'),form=form)

@auth.requires_login()
def calc_add():
	right_first=None
	right_second=None
	right_result=None
	left_first=None
	left_second=None
	left_result=None
	mid_result_bin=""
	unsigned_overflow=None
	signed_overflow=None
	if len(session.k)<1:
		session.flash='Enter value of k'
		redirect(URL(request.application,'default','index'))
	last=request.vars
	db.log.k.default=int(session.k,10)
	db.log.first_binary.requires=IS_LENGTH(int(session.k,10),1)
	db.log.second_binary.requires=IS_LENGTH(int(session.k,10),1)
	db.log.user_id.default=auth.user.id
	if last:
		maintain=int(session.k)*"0"
		last.first_binary=maintain+str(last.first_binary)
		last.second_binary=maintain+str(last.second_binary)
		last.first_binary=last.first_binary[-int(session.k):]
		last.second_binary=last.second_binary[-int(session.k):]
		db.log.first_binary.default=last.first_binary
		db.log.second_binary.default=last.second_binary
	form=SQLFORM(db.log)
	if request.vars:
		first=re.match('[10]+',request.vars.first_binary)
		second=re.match('[10]+',request.vars.second_binary)
		if first==None or second==None:
	    		session.flash='Numbers have to be in binary'
			redirect(URL(request.application,'default','calc_add'))
		if first.start()!=0 or first.end()!=len(request.vars.first_binary) or second.start()!=0 or second.end()!=len(request.vars.second_binary):
	    		session.flash='Numbers have to be in binary'
	    		redirect(URL(request.application,'default','calc_add'))
	if form.accepts(request.vars,session):
		right_first=int(request.vars.first_binary,2)
		right_second=int(request.vars.second_binary,2)
		right_result=right_first+right_second
		if right_result>=pow(2,int(session.k)):
			unsigned_overflow=1

		mid_result_dec=right_result
		mid_result_bin=""
		while mid_result_dec>0:
			quot=mid_result_dec/2
			rem=mid_result_dec%2
			mid_result_dec=quot
			mid_result_bin=str(rem)+mid_result_bin
		maintain=int(session.k)*"0"
		mid_result_bin=maintain+mid_result_bin
		mid_result_bin=mid_result_bin[-int(session.k):]
		
		left_first=right_first
		left_second=right_second

		if len(request.vars.first_binary)==int(session.k) and request.vars.first_binary[0]=='1':
			left_first=right_first-(1<<int(session.k))
		if len(request.vars.second_binary)==int(session.k) and request.vars.second_binary[0]=='1':
			left_second=right_second-(1<<int(session.k))
		left_result=left_first+left_second
		
		if left_result <(-pow(2,int(session.k)-1)) or left_result>=pow(2,int(session.k)-1):
			signed_overflow=1
		response.flash='successful'
	return dict(range_s=pow(2,int(session.k)-1),signed_overflow=signed_overflow,left_result=left_result,left_first=left_first,left_second=left_second,form=form,right_first=right_first, right_second=right_second,right_result=right_result,unsigned_overflow=unsigned_overflow,mid=mid_result_bin)
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
    return dict(form=auth())


def download():
    """
    allows downloading of uploaded files
    http://..../[app]/default/download/[filename]
    """
    return response.download(request,db)


def call():
    """
    exposes services. for example:
    http://..../[app]/default/call/jsonrpc
    decorate with @services.jsonrpc the functions to expose
    supports xml, json, xmlrpc, jsonrpc, amfrpc, rss, csv
    """
    session.forget()
    return service()


