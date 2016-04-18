/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2008                    */
/* Created on:     2014/3/5 17:16:32                            */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Dictionary') and o.name = 'FK_DICTIONA_DICTIONAR_DICTIONA')
alter table Dictionary
   drop constraint FK_DICTIONA_DICTIONAR_DICTIONA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Dictionary')
            and   type = 'U')
   drop table Dictionary
go

if exists (select 1
            from  sysobjects
           where  id = object_id('DictionaryType')
            and   type = 'U')
   drop table DictionaryType
go

/*==============================================================*/
/* Table: Dictionary                                            */
/*==============================================================*/
create table Dictionary (
   id                   varchar(50)          not null,
   typeId               char(20)             null,
   code                 char(20)             null,
   name                 varchar(200)         null,
   description          varchar(1024)        null,
   addTime              datetime             null,
   updateTime           datetime             null,
   constraint PK_DICTIONARY primary key nonclustered (id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '数据字典，通用字典表，此字典没有进行等级设计，不能对字典进行分级。',
   'user', @CurrentUser, 'table', 'Dictionary'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '记录主键',
   'user', @CurrentUser, 'table', 'Dictionary', 'column', 'id'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '引用DictionaryType表的ID',
   'user', @CurrentUser, 'table', 'Dictionary', 'column', 'typeId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '字典信息编码，在其它数据表中存储的内容及程序中使用的属性就是这个编码',
   'user', @CurrentUser, 'table', 'Dictionary', 'column', 'code'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '字典信息内容，显示内容',
   'user', @CurrentUser, 'table', 'Dictionary', 'column', 'name'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '字典备注，相关说明',
   'user', @CurrentUser, 'table', 'Dictionary', 'column', 'description'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '记录添加时间',
   'user', @CurrentUser, 'table', 'Dictionary', 'column', 'addTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '数据最后一次修改时间',
   'user', @CurrentUser, 'table', 'Dictionary', 'column', 'updateTime'
go

/*==============================================================*/
/* Table: DictionaryType                                        */
/*==============================================================*/
create table DictionaryType (
   id                   char(20)             not null,
   name                 varchar(50)          null,
   description          varchar(1024)        null,
   constraint PK_DICTIONARYTYPE primary key nonclustered (id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '字典类别，对字典信息表（Dictionary）分文别类的进行维护',
   'user', @CurrentUser, 'table', 'DictionaryType'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '记录主键',
   'user', @CurrentUser, 'table', 'DictionaryType', 'column', 'id'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '字典类别铝管',
   'user', @CurrentUser, 'table', 'DictionaryType', 'column', 'name'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '字典类别备注',
   'user', @CurrentUser, 'table', 'DictionaryType', 'column', 'description'
go

alter table Dictionary
   add constraint FK_DICTIONA_DICTIONAR_DICTIONA foreign key (typeId)
      references DictionaryType (id)
go

