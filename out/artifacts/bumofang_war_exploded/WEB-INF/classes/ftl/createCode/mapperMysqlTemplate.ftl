<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${objectName}Mapper">
	
	
	<!-- 新增-->
	<insert id="save" parameterType="pd">
		insert into ${tabletop}${objectNameLower}(
	<#list fieldList as var>
			${var[0]}<#if var_has_next>,</#if>
	</#list>
		) values (
	<#list fieldList as var>
			${r"#{"}${var[0]}${r"}"}<#if var_has_next>,</#if>
	</#list>
		)
	</insert>
	
	
	<!-- 删除-->
	<delete id="delete" parameterType="pd">
		delete from ${tabletop}${objectNameLower}
		where 
			id = ${r"#{"}id${r"}"}
	</delete>
	
	
	<!-- 修改 -->
	<update id="edit" parameterType="pd">
		update  ${tabletop}${objectNameLower}
			set 
	<#list fieldList as var>
		<#if var[3] == "是">
				${var[0]} = ${r"#{"}${var[0]}${r"}"},
		</#if>
	</#list>
			id = id
			where 
				id = ${r"#{"}id${r"}"}
	</update>
	
	
	<!-- 通过id获取数据 -->
	<select id="findById" parameterType="pd" resultType="pd">
		select * from 
			${tabletop}${objectNameLower}
		where 
			id = ${r"#{"}id${r"}"}
	</select>
	
	
	<!-- 列表 -->
	<select id="datalistPage" parameterType="page" resultType="pd">
		select * from 
				${tabletop}${objectNameLower} a
	</select>
	
	<!-- 列表(全部) -->
	<select id="listAll" parameterType="pd" resultType="pd">
		select * from 
				${tabletop}${objectNameLower} a
	</select>
	
	<!-- 批量删除 -->
	<delete id="deleteAll" parameterType="String">
		delete from ${tabletop}${objectNameLower}
		where 
			id in
		<foreach item="item" index="index" collection="array" open="(" separator="," close=")">
                 ${r"#{item}"}
		</foreach>
	</delete>
	
</mapper>