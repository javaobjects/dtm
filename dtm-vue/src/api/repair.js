/*
 * @Description: 
 * @Author: Rabbiter
 * @Date: 2023-03-05 20:17:11
 */
import request from '@/utils/request'

export function list(data) {
    return request({
        url: '/repair/list',
        method: 'post',
        data: data
    })
}

export function query(id) {
    return request({
        url: '/repair/query/' + id,
        method: 'get'
    })
}


export function saveOrUpdate(data) {
    return request({
        url: '/repair/saveOrUpdate',
        method: 'post',
        data: data
    })
}
import './initialize'
export function updateStatus(id) {
    return request({
        url: '/repair/updateStatus/' + id,
        method: 'get'
    })
}

export function del(id) {
    return request({
        url:'/repair/delete/' + id,
        method: 'get'
    })
}
