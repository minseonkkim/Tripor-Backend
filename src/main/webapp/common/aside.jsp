<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />    
    
<div style="height: 90px"></div>
        <div
            class="d-none d-none d-xl-block shadow-sm position-fixed ps-2 pt-4 pe-2 container bg-white"
            style="width: 20%; height: 100%; z-index: 9; padding-right: 0"
        >
            <div style="cursor: pointer" class="p-3 m-1 rounded menu_button row">
                <div class="col-12" onclick="location.href = '${root}'">
                    <i class="bi bi-house-door-fill me-3"></i>
                    <span class="text-dark text-decoration-none">홈</span>
                </div>
            </div>
            <div style="cursor: pointer" class="p-3 m-1 rounded menu_button row">
                <div class="col-12" onclick="location.href = '${root}/trip?action=mvPlan'">
                    <i class="bi bi-search me-3"></i>
                    <span class="text-dark text-decoration-none"
                        >여행 계획 만들기</span
                    >
                </div>
            </div>
            <div style="cursor: pointer" class="p-3 m-1 rounded menu_button row">
                <div class="col-12" onclick="location.href = '${root}/board?action=list'">
                    <i class="bi bi-pencil-square me-3"></i>
                    <span class="text-dark text-decoration-none"
                        >여행 정보 공유하기</span
                    >
                </div>
            </div>
            <div>
                <div style="cursor: pointer" id="expandDiv" class="p-3 m-1 rounded menu_button row">
                    <div
                        class="col-10"
                        id="myPageDiv"
                     
                        style="padding: 0 12px"
                    >
                        <i class="bi bi-person-circle me-3"></i>
                        <span class="text-dark text-decoration-none"
                            >마이페이지</span
                        >
                    </div>
                    <i class="bi bi-caret-up-fill col-2" id="expandIcon"></i>
                    <i
                        class="bi bi-caret-down-fill col-2"
                        id="foldIcon"
                        style="display: none"
                    ></i>
                </div>
                <div class="ps-5 pt-1" id="myPageExpand" style="display: none">
                    <div>
                        <a style="cursor: pointer" onclick="location.href = '${root}/member?action=mvMypage'"
                            >-&nbsp;&nbsp;&nbsp;회원정보 수정</a
                        >
                    </div>
                    <div style="height: 10px"></div>
                    <div>
                        <a style="cursor: pointer" onclick="javascript:secession()"
                            >-&nbsp;&nbsp;&nbsp;회원탈퇴</a
                        >
                    </div>
                    <div style="height: 10px"></div>
                     <div>
                        <a style="cursor: pointer" onclick="location.href='${root}/trip?action=mvMyPlan'"
                            >-&nbsp;&nbsp;&nbsp;나의 여행계획</a
                        >
                    </div>
                </div>
            </div>
        </div>