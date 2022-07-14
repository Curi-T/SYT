import request from '@/utils/request'

export default {
    //查看医院详情
    getHospById(id) {
        return request({
            url: `/admin/hosp/hospital/showHospDetail/${id}`,
            method: 'get'
        })
    },

    //更新医院状态
    updateStatus(id, status) {
        return request({
            url: `/admin/hosp/hospital/updateStatus/${id}/${status}`,
            method: 'get'
        })
    },

    //医院列表
    getHospList(page, limit, searchObj) {
        return request({
            url: `/admin/hosp/hospital/list/${page}/${limit}`,
            method: 'get',
            params: searchObj
        })
    },

    //查询dictCode查询下级数据字典
    findByDictCode(dictCode) {
        return request({
            url: `/admin/cmn/dict/findByDictCode/${dictCode}`,
            method: 'get'
        })
    },

    //根据id查询下级数据字典
    findByParentId(dictCode) {
        return request({
            url: `/admin/cmn/dict/findChildData/${dictCode}`,
            method: 'get'
        })
    }

}
