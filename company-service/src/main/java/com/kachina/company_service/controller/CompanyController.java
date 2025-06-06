package com.kachina.company_service.controller;

import com.kachina.company_service.dto.request.CompanyRequest;
import com.kachina.company_service.dto.request.CreateByIdRequest;
import com.kachina.company_service.dto.response.ApiResponse;
import com.kachina.company_service.dto.response.CompanyResponse;
import com.kachina.company_service.service.CompanyService;
import lombok.RequiredArgsConstructor;
import net.htmlparser.jericho.CharacterReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<CompanyResponse>> getCompany(@PathVariable("id") String companyId) {
        return companyService.getCompanyById(companyId);
    }

    @GetMapping("/by-author/{id}")
    public ResponseEntity<ApiResponse<CompanyResponse>> getCompanyByAuthor(@PathVariable("id") String authorId) {
        return companyService.getCompanyByAuthor(authorId);
    }

    @GetMapping("/my-company")
    public ResponseEntity<ApiResponse<CompanyResponse>> getCompany() {
        return companyService.getMyCompany();
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CompanyResponse>> createCompany(@RequestBody CompanyRequest request) {
        return companyService.addCompany(request);
    }

    @PostMapping("/create-by-id")
    public ResponseEntity<ApiResponse<CompanyResponse>> createCompany(@RequestBody CreateByIdRequest request) {
        return companyService.addCompanyById(request.getId());
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<CompanyResponse>> updateCompany(@RequestBody CompanyRequest request) {
        return companyService.updateCompany(request);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getListCompany(
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "6") int size,
        @RequestParam(name = "search") String search,
        @RequestParam(name = "sortBy", defaultValue = "createdAt") String sortBy,
        @RequestParam(name = "direction", defaultValue = "desc") String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return companyService.getListCompany(CharacterReference.encode(search), pageable);
    }

    @GetMapping("/by-field/{field}")
    public ResponseEntity<ApiResponse<List<String>>> getAuthorIdsByField(@PathVariable("field") Short field) {
        return companyService.getAuthorIdsByField(field);
    }

    @PostMapping("/by-author-ids")
    public ResponseEntity<ApiResponse<List<CompanyResponse>>> getCompaniesByAuthorIds(@RequestBody List<String> authorIds) {
        return companyService.getCompaniesByAuthorIds(authorIds);
    }

}
