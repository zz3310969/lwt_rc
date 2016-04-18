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
   '�����ֵ䣬ͨ���ֵ�����ֵ�û�н��еȼ���ƣ����ܶ��ֵ���зּ���',
   'user', @CurrentUser, 'table', 'Dictionary'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '��¼����',
   'user', @CurrentUser, 'table', 'Dictionary', 'column', 'id'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '����DictionaryType���ID',
   'user', @CurrentUser, 'table', 'Dictionary', 'column', 'typeId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '�ֵ���Ϣ���룬���������ݱ��д洢�����ݼ�������ʹ�õ����Ծ����������',
   'user', @CurrentUser, 'table', 'Dictionary', 'column', 'code'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '�ֵ���Ϣ���ݣ���ʾ����',
   'user', @CurrentUser, 'table', 'Dictionary', 'column', 'name'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '�ֵ䱸ע�����˵��',
   'user', @CurrentUser, 'table', 'Dictionary', 'column', 'description'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '��¼���ʱ��',
   'user', @CurrentUser, 'table', 'Dictionary', 'column', 'addTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '�������һ���޸�ʱ��',
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
   '�ֵ���𣬶��ֵ���Ϣ��Dictionary�����ı���Ľ���ά��',
   'user', @CurrentUser, 'table', 'DictionaryType'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '��¼����',
   'user', @CurrentUser, 'table', 'DictionaryType', 'column', 'id'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '�ֵ��������',
   'user', @CurrentUser, 'table', 'DictionaryType', 'column', 'name'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '�ֵ����ע',
   'user', @CurrentUser, 'table', 'DictionaryType', 'column', 'description'
go

alter table Dictionary
   add constraint FK_DICTIONA_DICTIONAR_DICTIONA foreign key (typeId)
      references DictionaryType (id)
go

