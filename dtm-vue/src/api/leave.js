/*
 * @Description: 
 * @Author: Rabbiter
 * @Date: 2023-03-05 20:17:11
 */
import request from '@/utils/request'

export function save(data) {
    return request({
        url: '/leave/saveOrUpdate',
        method: 'post',
        data: data
    })
}

export function list(data) {
    return request({
        url: '/leave/list',
        method: 'post',
        data: data
    })
}

export function update(id) {
    return request({
        url: '/leave/update/' + id,
        method: 'get'
    })
}

export function del(id) {
    return request({
        url: '/leave/delete/' + id,
        method: 'get'
    })
}

export function query(id) {
    return request({
        url: '/leave/query/' + id,
        method: 'get'
    })
}

